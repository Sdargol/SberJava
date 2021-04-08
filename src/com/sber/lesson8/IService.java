package com.sber.lesson8;

import java.util.Date;
import java.util.List;

public interface IService {
    @Cache(cacheType = CacheType.FILE ,
            cacheFileName = "runTest",
            identityBy = {String.class,String.class, Double.class},
            listSize = 3,
            toZip = true)
    List<String> run(String item, double value, Date date);
}
