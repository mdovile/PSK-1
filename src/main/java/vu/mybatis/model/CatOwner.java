package vu.mybatis.model;

public class CatOwner {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.CAT_OWNER.CATS_ID
     *
     * @mbg.generated Sun Mar 21 13:01:16 EET 2021
     */
    private Integer catsId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.CAT_OWNER.OWNERS_ID
     *
     * @mbg.generated Sun Mar 21 13:01:16 EET 2021
     */
    private Integer ownersId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.CAT_OWNER.CATS_ID
     *
     * @return the value of PUBLIC.CAT_OWNER.CATS_ID
     *
     * @mbg.generated Sun Mar 21 13:01:16 EET 2021
     */
    public Integer getCatsId() {
        return catsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.CAT_OWNER.CATS_ID
     *
     * @param catsId the value for PUBLIC.CAT_OWNER.CATS_ID
     *
     * @mbg.generated Sun Mar 21 13:01:16 EET 2021
     */
    public void setCatsId(Integer catsId) {
        this.catsId = catsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.CAT_OWNER.OWNERS_ID
     *
     * @return the value of PUBLIC.CAT_OWNER.OWNERS_ID
     *
     * @mbg.generated Sun Mar 21 13:01:16 EET 2021
     */
    public Integer getOwnersId() {
        return ownersId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.CAT_OWNER.OWNERS_ID
     *
     * @param ownersId the value for PUBLIC.CAT_OWNER.OWNERS_ID
     *
     * @mbg.generated Sun Mar 21 13:01:16 EET 2021
     */
    public void setOwnersId(Integer ownersId) {
        this.ownersId = ownersId;
    }
}