308Grader
=========

Basic git instructions for new users:

(ask James or Erik to help you clone the repository)

svn update ->
git pull origin master

svn commit SomeFile.java ->
git add SomeFile.java
git commit -m "Commit message goes here"
git push origin master

svn blame SomeFile.java ->
git blame SomeFile.java

svn status ->
git status

svn rm SomeFile.java ->
git rm SomeFile.java
git commit -m "Deleted SomeFile.java from repository"
git push origin master
