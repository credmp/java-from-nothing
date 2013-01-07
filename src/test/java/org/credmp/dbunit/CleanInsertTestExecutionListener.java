package org.credmp.dbunit;

import java.util.Calendar;
import java.util.Locale;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;
import org.springframework.util.StringUtils;

/**
 * @author acogoluegnes
 *
 */
public class CleanInsertTestExecutionListener implements TestExecutionListener {
    private final static Logger logger = LoggerFactory.getLogger(CleanInsertTestExecutionListener.class); 


    /* (non-Javadoc)
     * @see org.springframework.test.context.TestExecutionListener#beforeTestMethod(org.springframework.test.context.TestContext)
     */
    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        DefaultDataTypeFactory typeFactory = testContext.getApplicationContext().getBean(DefaultDataTypeFactory.class);

        // trying to find the DbUnit dataset
        String dataSetResourcePath = null;

        // first, the annotation on the test class
        DataSetLocation dsLocation = testContext.getTestInstance().getClass().getAnnotation(DataSetLocation.class);
        if(dsLocation != null) {
            // found the annotation
            dataSetResourcePath = dsLocation.value();
            logger.info("annotated test, using data set: {}",dataSetResourcePath);
        } else {
            // no annotation, let's try with the name of the test
            String tempDsRes = testContext.getTestInstance().getClass().getName();
            tempDsRes = StringUtils.replace(tempDsRes, ".", "/");
            tempDsRes = "/"+tempDsRes+"-dataset.xml";
            if(getClass().getResourceAsStream(tempDsRes) != null) {
                logger.info("detected default dataset: {}",tempDsRes);
                dataSetResourcePath = tempDsRes;
            } else {
                logger.info("no default dataset");
            }
        }

        if(dataSetResourcePath != null) {
            Resource dataSetResource = testContext.getApplicationContext().getResource(dataSetResourcePath);
            IDataSet dataSet = new FlatXmlDataSetBuilder().build(dataSetResource.getInputStream());
            ReplacementDataSet replaceDataSet = new ReplacementDataSet(dataSet);
            replaceDataSet.addReplacementObject("[NULL]", null);
            Calendar cal = Calendar.getInstance(Locale.getDefault());
            replaceDataSet.addReplacementObject("[NOW]", cal.getTime());
            IDatabaseConnection dbConn = new DatabaseDataSourceConnection(
                                                                          testContext.getApplicationContext().getBean(DataSource.class)
                                                                          );
            DatabaseConfig config = dbConn.getConfig();

            config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, typeFactory);
            DatabaseOperation.CLEAN_INSERT.execute(dbConn, replaceDataSet);
        } else {
            logger.info("{} does not have any data set, no data injection",testContext.getClass().getName());
        }

    }

    /* (non-Javadoc)
     * @see org.springframework.test.context.TestExecutionListener#beforeTestClass(org.springframework.test.context.TestContext)
     */
    @Override
    public void beforeTestClass(TestContext testContext) throws Exception { }

    /* (non-Javadoc)
     * @see org.springframework.test.context.TestExecutionListener#prepareTestInstance(org.springframework.test.context.TestContext)
     */
    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception { }

    /* (non-Javadoc)
     * @see org.springframework.test.context.TestExecutionListener#afterTestMethod(org.springframework.test.context.TestContext)
     */
    @Override
    public void afterTestMethod(TestContext testContext) throws Exception { }

    /* (non-Javadoc)
     * @see org.springframework.test.context.TestExecutionListener#afterTestClass(org.springframework.test.context.TestContext)
     */
    @Override
    public void afterTestClass(TestContext testContext) throws Exception { }

}
