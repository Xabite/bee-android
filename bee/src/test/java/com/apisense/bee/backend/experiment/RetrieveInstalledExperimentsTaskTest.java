//package com.apisense.bee.backend.experiment;
//
//import com.apisense.bee.AsyncTaskWithCallbacksTestSuite;
//import com.apisense.bee.BeeRobolectricTestRunner;
//import org.junit.runner.RunWith;
//
//@RunWith(BeeRobolectricTestRunner.class)
//public class RetrieveInstalledExperimentsTaskTest extends AsyncTaskWithCallbacksTestSuite {
//
//    private RetrieveInstalledExperimentsTask task;
//
//    @Before
//    public void setUp() throws Exception {
//        super.setUp();
//        task =  new RetrieveInstalledExperimentsTask(new BeeSenseServiceManagerMock(), this);
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        BeeSenseMobileServiceMock.installedExperiments = new HashMap<String, Experiment>();
//    }
//
//    @Test
//    public final void testNullReturned() throws InterruptedException {
//        BeeSenseMobileServiceMock.installedExperiments = null;
//
//        task.execute();
//        signal.await(15, TimeUnit.SECONDS);
//
//        Assert.assertNotSame(result, Integer.MIN_VALUE);
//        Assert.assertEquals(BeeApplication.ASYNC_SUCCESS, result);
//
//        Assert.assertNotNull(response);
//        Assert.assertTrue(response instanceof List);
//        Assert.assertTrue(((List) response).isEmpty());
//    }
//
//    @Test
//    public final void testEmptyMapReturned() throws InterruptedException {
//        BeeSenseMobileServiceMock.installedExperiments = new HashMap<String, Experiment>();
//
//        task.execute();
//        signal.await(15, TimeUnit.SECONDS);
//
//        Assert.assertNotSame(result, Integer.MIN_VALUE);
//        Assert.assertEquals(BeeApplication.ASYNC_SUCCESS, result);
//
//        Assert.assertNotNull(response);
//        Assert.assertTrue(response instanceof List);
//        Assert.assertTrue(((List) response).isEmpty());
//    }
//
//    @Test
//    public final void testMapReturned() throws JSONException, InterruptedException {
//        Experiment exp1 = new Experiment(getExperimentJson());
//        Experiment exp2 = new Experiment(getExperimentJson());
//        Map<String, Experiment> returnedMap= new HashMap<String, Experiment>();
//        returnedMap.put("exp1", exp1);
//        returnedMap.put("exp2", exp2);
//        BeeSenseMobileServiceMock.installedExperiments = returnedMap;
//
//        task.execute();
//        signal.await(15, TimeUnit.SECONDS);
//
//        Assert.assertNotSame(result, Integer.MIN_VALUE);
//        Assert.assertEquals(BeeApplication.ASYNC_SUCCESS, result);
//
//        Assert.assertNotNull(response);
//        Assert.assertTrue(response instanceof List);
//        Assert.assertFalse(((List) response).isEmpty());
//        Assert.assertTrue(((List) response).contains(exp1));
//        Assert.assertTrue(((List) response).contains(exp2));
//    }
//}
