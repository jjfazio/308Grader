package model.spreadsheet;

import java.io.Serializable;

/**
 * Object representing a LatePolicy for an overall class or individual
 * assignment.
 */
public class LatePolicy implements Serializable {

    private static final long serialVersionUID = 433070988168712411L;

    /**
     * Boolean that indicates whether graceDays are enabled. Grace Days
     * are days that a student have to turn in an assignment without penalty.
     * If late days are assigned at a category level, no sub-categories can
     * override the amount of graceDays. Multiple same level categories can
     * have different amounts of graceDays.
     */
   private Boolean graceDaysEnabled;
   
   /**
    * The amount of time a student has before they start to get deducted.
    * The decayRate is an amount of time in hours.
    */
   private Integer decayRate;
   
   /**
    * The amount of percentage deducted from a student's grade if they turn in 
    * and assignment late. If the decayRate is 1 hour and the decayPercentage is
    * 10% a student will lose 20% if they turn in an assignment 2 hours late.
    */
   private Double decayPercentage;
   
   /**
    * The amount of graceDays a student has.
    */
   private Integer graceDays;
   

   public LatePolicy(Boolean graceDaysEnabled, Integer decayRate,
           Double decayPercentage, Integer graceDays)
   {
       super();
       this.graceDaysEnabled = graceDaysEnabled;
       this.decayRate = decayRate;
       this.decayPercentage = decayPercentage;
       this.graceDays = graceDays;
   }
   
   /**
    * Default constructor that sets valuse to 0 and false.
    */
   public LatePolicy()
   {
       super();
       this.graceDaysEnabled = false;
       this.decayRate = 0;
       this.decayPercentage = 0.0;
       this.graceDays = 0;
   }

   public Boolean getGraceDaysEnabled()
   {
       return graceDaysEnabled;
   }

   public Integer getDecayRate()
   {
       return decayRate;
   }

   public Double getDecayPercentage()
   {
       return decayPercentage;
   }

   public Integer getGraceDays()
   {
       return graceDays;
   }
   public void setGraceDaysEnabled(boolean graceDays)
   {
       graceDaysEnabled = graceDays;
   }

   public void setDecayRate(int rate)
   {
       decayRate = rate;
   }

   public void setDecayPercentage(Double percent)
   {
       decayPercentage = percent;
   }

   public void setGraceDays(int days)
   {
       graceDays = days;
   }
   
   
  
   
   
}
