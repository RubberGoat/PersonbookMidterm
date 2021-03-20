/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonbookMT;


/**
 *
 * @author Aldo
 */
public class Person {
    //person attributes
   
    private int ID;
    private String FName;
    private String LName;
    private int BD;
    private int BM;
    private String BY;
    private Boolean PersonalReason;
    private Boolean BusinessReasons;
    private String CN;

    public Person(int ID,String FName, String LName, int BD, int BM, String BY, Boolean PersonalReason, Boolean BusinessReasons, String CN) {
       
        this.ID = ID;
        this.FName = FName;
        this.LName = LName;
        this.BD = BD;
        this.BM = BM;
        this.BY = BY;
        this.PersonalReason = PersonalReason;
        this.BusinessReasons = BusinessReasons;
        this.CN = CN;
    }

    
    /**
     * @return the FName
     */
    public String getFName() {
        return FName;
    }

    /**
     * @param FName the FName to set
     */
    public void setFName(String FName) {
        this.FName = FName;
    }

    /**
     * @return the LName
     */
    public String getLName() {
        return LName;
    }

    /**
     * @param LName the LName to set
     */
    public void setLName(String LName) {
        this.LName = LName;
    }

    /**
     * @return the PersonalReason
     */
    public Boolean getPersonalReason() {
        return PersonalReason;
    }

    /**
     * @param PersonalReason the PersonalReason to set
     */
    public void setPersonalReason(Boolean PersonalReason) {
        this.PersonalReason = PersonalReason;
    }

    /**
     * @return the BusinessReasons
     */
    public Boolean getBusinessReasons() {
        return BusinessReasons;
    }

    /**
     * @param BusinessReasons the BusinessReasons to set
     */
    public void setBusinessReasons(Boolean BusinessReasons) {
        this.BusinessReasons = BusinessReasons;
    }

    /**
     * @return the BD
     */
    public int getBD() {
        return BD;
        
    }

    /**
     * @param BD the BD to set
     */
    public void setBD(int BD) {
        this.BD = BD;
    }


    /**
     * @return the CN
     */
    public String getCN() {
        return CN;
    }

    /**
     * @param CN the CN to set
     */
    public void setCN(String CN) {
        this.CN = CN;
    }

    /**
     * @return the BM
     */
    public int getBM() {
        return BM;
    }

    /**
     * @param BM the BM to set
     */
    public void setBM(int BM) {
        this.BM = BM;
    }

    /**
     * @return the BY
     */
    public String getBY() {
       
        
            
        return BY;
    }

    /**
     * @param BY the BY to set
     */
    public void setBY(String BY) {
        this.BY = BY;
    }
    
   
    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

 @Override
    public String toString(){
        return this.FName + " " + this.LName;
    }


}
