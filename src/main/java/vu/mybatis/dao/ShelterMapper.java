package vu.mybatis.dao;

import java.util.List;

import org.mybatis.cdi.Mapper;
import vu.mybatis.model.Shelter;

@Mapper
public interface ShelterMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SHELTER
     *
     * @mbg.generated Sun Mar 21 13:01:16 EET 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SHELTER
     *
     * @mbg.generated Sun Mar 21 13:01:16 EET 2021
     */
    int insert(Shelter record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SHELTER
     *
     * @mbg.generated Sun Mar 21 13:01:16 EET 2021
     */
    Shelter selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SHELTER
     *
     * @mbg.generated Sun Mar 21 13:01:16 EET 2021
     */
    List<Shelter> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SHELTER
     *
     * @mbg.generated Sun Mar 21 13:01:16 EET 2021
     */
    int updateByPrimaryKey(Shelter record);
}