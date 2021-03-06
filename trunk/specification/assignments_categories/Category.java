package assignments_categories;
import java.util.ArrayList;
import java.util.Collection;

/**
 *	A category is a way for a teacher to group different assignments. 
 *	Therefore, it consists of a name, a collection of assignments, and a weight.
 *	The weight represents what portion of a student's final grade this category is worth. 
 */

public class Category {
   Category parentCategory;
	String name;
	Collection<Assignment> assignments;
	Collection<Category> subCategories;
	Double percentofparent;
	Double percentCurve;
	
	public Category () {
	   this(null, 100.0, "Total");
	}
	
	public Category(Category parentCategory, Double percentOfParent, String name) {
	   this.parentCategory = parentCategory;
	   this.percentofparent = percentOfParent;
	   this.name = name;
	   
	   if (parentCategory != null)
	      parentCategory.addSubCategory(this);
	}
	
	
	/**
    * Adds assignments to the spreadsheet under the specified category
    */
   /*@
   requires
    //
    // The parentCategory is not null
    //
    (!assignment.parentCategory.equals(null))

   &&

   //
   // The assignmentName is not null or an empty string.
   //
   (!assignment.assignmentName.equals("") && !assignment.assignmentName.equals(null))

    &&

    //
    // The weight is greater or equal to zero and less than or equal to 100 and not equal to null or empty string
    //
    (!assignment.weight.equals("") && !assignment.weight.equals(null) && Double.parseDouble(assignment.weight) >= 0 && Double.parseDouble(assignment.weight) <= 100
    && Double.parseDouble(assignment.weight))

    &&

    //
    // The dueDate is not an empty string or null and it has valid date format
    //
    (!assignment.dueDate.equals("") && !assignment.dueDate.equals(null) && assignment.dueDate.indexOf(dueDate) == 2 &&
     assignment.dueDate.reverseIndexOf(dueDate) == 4)

    &&

    //
    // The points is not null or empty string, and it is greater or equal to zero
    //
    (!assignment.points.equals("") && !assignment.points.equals(null) && Integer.parseInt(assignment.points) >= 0);

   ensures
   //
   // The total number of assignments for the parentCategory has increased by one.
   //
   (assignment.parentCategory.assignments.size().equals(\old(assignment.parentCategory).assignments.size() + 1))

   &&

   //
   // The category with name of parentCategory.name in the spreadsheet has an assignment names assignmentName.
   //
   (\exists Assignment newAssignment; parentCategory.contains(assignmentName));
    @*/

   public void addAssignment(Assignment assignment) {
      
   }

   /**
    * Edits the name, weight, due date, grading scheme, late policy, and electronic turn in  of assignments.
    */
   /*@
   requires
    //
    // The parentCategory is not null or an empty string.
    //

   (!parentCategory.equals(null) && !assignmentName.equals(null))

    &&

   //
   // The assignmentName is not null or an empty string.
   //
   (!assignmentName.equals("") && !assignmentName.equals(null))
   
    &&

    //
    // The weight is greater or equal to zero and less than or equal to 100 and not equal to null or empty string
    //
    (!weight.equals("") && !weight.equals(null) && Double.parseDouble(weight) >= 0 && Double.parseDouble(weight) <= 100
    && Double.parseDouble(weight))

    &&

    //
    // The dueDate is not an empty string or null and it has valid date format
    //
    (!dueDate.equals("") && !dueDate.equals(null) && dueDate.indexOf(dueDate) == 2 && dueDate.reverseIndexOf(dueDate) == 4)

    &&

    //
    // The points is not null or empty string, and it is greater or equal to zero
    //
    (!points.equals("") && !points.equals(null) && Integer.parseInt(points) >= 0);


   ensures
   //
   // The total number of assignments for the parentCategory has stayed the same.
   //
   (parentCategory.assignments.size().equals(\old(parentCategory).assignments.size()))

   &&

   //
   // The category with name of parentCategory.name in the spreadsheet has an assignment names assignmentName.
   //
   (\exists Assignment editAssignment; parentCategory.contains(assignmentName));
    @*/
   public void editAssignment(Assignment oldAssignment, Assignment newAssignment) {
      
   }


   /**
    * Removes an assignment from the spreadsheet and from collection of assignments of parent category.
    */
   /*@
   requires
    //
    // The parentCategory is not null
    //
    (!parentCategory.equals(null))

   &&

   //
   // The assignmentName is not null or an empty string and parentCategory has an assignment delAssignment
   //
   (!assignmentName.equals("") && !assignmentName.equals(null) && parentCategory.assignments.contains);

   ensures
   //
   // The total number of assignments for the parentCategory has decreased by one.
   //
   (parentCategory.assignments.size().equals(\old(parentCategory).assignments.size() - 1))

   &&

   //
   // The category with name of parentCategory.name in the spreadsheet does not have an assignment names assignmentName.
   //
   !(assignments.contains(assignment));
    @*/
   public void removeAssignment(Assignment assignment) {
      
   }
   
   /*@
   requires
   //
   // The weight of the new category is not less than zero or greater
   // than one hundred percent
   //
   (category.percentOfParent >= 0 && category.percentOfParent <= 1.0)
   
   &&
   
   //
   //the parentCategory exists
   //
   (!\exists Category parentCat; course.contains(category.parentCategory));
   
   ensures
   //
   // The total number of categories for the current spreadsheet has
   // increased by one.
   (course.categories.size().equals(\old(course).categories.size() + 1))
   
   &&
   
   //
   // One of the categories in the currently modified spreadsheet course
   // has a name with the same string passed in to the addCategory method.
   (!\exists Category cat; cat.name.equals(category.name));
   @*/
   
   public void addSubCategory(Category category) {
      if (subCategories == null) {
         subCategories = new ArrayList<Category>();
      }
      
      subCategories.add(category);
   }
  
  /*@
   requires
   //
   //New Category must contain data in it (must not be null)
   //
   (newCategory != null)
   
   &&
   
   //
   // The weight of the new category is not less than zero or greater
   // than one hundred percent
   //
   (newCategory.percentOfParent >= 0 && newCategory.percentOfParent <= 1.0)
   
   &&
   
   //
   //the parentCategory exists
   //
   (\exists Category parentCat; this.contains(newCategory.parentCategory));
   
   ensures
   
   //
   // One of the categories in the currently modified spreadsheet course
   // has a name with the same string passed in to the addCategory method.
   
   (\exists Category cat; cat.name.equals(newCategory.name));
   
  @*/
   /**
    * Edits the previously defined categories by changing their name and weight.
    */
   public void editCategory(Category newCategory) {
      
   }
  
  /* TODO: CHANGE THIS JML*/

   /**
    * Removed previously defined category from the category list
    */
  /*@
   requires
   //
   //The category is an existing category in the parent category
   //
   (\exists Category parentCategory; parentCategory.subcategories.contains(cateogry));
   
   ensures
   //
   // The total number of categories for the parent category has
   // decreased by one.
   (category.parentCategory.subCategories.size().equals(\old(category.parentCategory).subCategories.size() - 1))
   
   &&
   
   //
   // The parent category does not have the desired category that was to be deleted.
   (!category.parentCategory.contains(category));
   @*/
   public void removeCategory(Category category) {
      
   }

   public Category getParentCategory() {
      return parentCategory;
   }

//   public void setParentCategory(Category parentCategory) {
//      this.parentCategory = parentCategory;
//   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Double getPercentofparent() {
      return percentofparent;
   }

//   public void setPercentofparent(Double percentofparent) {
//      this.percentofparent = percentofparent;
//   }

   public Double getPercentCurve() {
      return percentCurve;
   }

   public void setPercentCurve(Double percentCurve) {
      this.percentCurve = percentCurve;
   }
   
   
    
}