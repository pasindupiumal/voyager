package com.voyager.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestDBConnection.class, TestFindByName.class, TestFindRoute.class, TestInsertRoute.class, TestUpdateRoute.class })
public class UnitTests {

}
