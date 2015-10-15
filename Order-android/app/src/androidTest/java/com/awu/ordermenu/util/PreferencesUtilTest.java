package com.awu.ordermenu.util;

import android.test.AndroidTestCase;

/**
 * Created by awu on 2015-10-14.
 */
public class PreferencesUtilTest extends AndroidTestCase {
    private  static final String TAG = "PreferencesUtil";
    public void testSetAndReadData(){
        try{
            PreferencesUtil.setData(getContext(),"string_key","string_value");
            PreferencesUtil.setData(getContext(),"bool_key",true);
            PreferencesUtil.setData(getContext(),"int_key",1);
            PreferencesUtil.setData(getContext(),"long_key",10L);
            PreferencesUtil.setData(getContext(),"float_key",1.123f);
            PreferencesUtil.setData("appcontext_int_key", 2);
            //normal retrieve value.
            assertEquals("string_value", PreferencesUtil.getData(getContext(), "string_key", ""));
            assertEquals(true, PreferencesUtil.getData(getContext(), "bool_key", false));
            assertEquals(1,PreferencesUtil.getData(getContext(),"int_key",0));
            assertEquals(10L,PreferencesUtil.getData(getContext(),"long_key",11L));
            assertEquals(1.123f,PreferencesUtil.getData(getContext(),"float_key",1.0f));
            assertEquals(2,PreferencesUtil.getData("appcontext_int_key",0));
            //no-key retrieve data,it will retrieve default value.
            assertEquals(123,PreferencesUtil.getData(getContext(),"no_key",123));
            assertEquals("string",PreferencesUtil.getData(getContext(),"no_key","string"));
            assertEquals(true,PreferencesUtil.getData(getContext(),"no_key",true));
            assertEquals(2L, PreferencesUtil.getData(getContext(), "no_key", 2L));
            assertEquals(1.23f,PreferencesUtil.getData(getContext(),"no_key",1.23f));
            assertEquals(123,PreferencesUtil.getData("no_key", 123));
            assertNotSame("string", PreferencesUtil.getData(getContext(), "no_key", "novalue"));
            assertNotSame(true, PreferencesUtil.getData(getContext(), "no_key", false));
            assertNotSame(2,PreferencesUtil.getData(getContext(),"no_key",4));
            assertNotSame(2L,PreferencesUtil.getData(getContext(),"no_key",1L));
            assertNotSame(1.23f,PreferencesUtil.getData(getContext(),"no_key",2.13f));
            assertNotSame("string",PreferencesUtil.getData(getContext(),"no_key",10));
        }catch(Exception ex){
            fail(TAG + " set data error:"+ex.getMessage());
        }
    }
}
