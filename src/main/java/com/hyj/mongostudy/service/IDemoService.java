package com.hyj.mongostudy.service;

import com.hyj.mongostudy.model.po.City;

/**
 * @author huyuanjia
 * @date 2019/4/2 17:11
 */
public interface IDemoService {
    City findCityById(String id);



    City saveCity(City city);

    City findCityByName(String name);

    City updateCity(City city);

    void deleteCity(Long id);

    void testAdd(City city);

    void testAdd2(City city);

    void testUpdate(City city);

    void testUpdate2(City city);

    void testDelete(City city);

    void testDelete2(City city);
}
