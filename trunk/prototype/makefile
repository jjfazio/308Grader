#
# Makefile for Grader Tool prototype.
#
CLASSPATH=.:/Library/Java/JavaVirtualMachines/jdk1.7.0_45/Contents/Home/jre/lib
MODULES = 	spreadsheet \
	users

FILES =		spreadsheet/*.java \
	users/*.java

all:	compile jar

compile:	$(FILES)
	javac -cp $(CLASSPATH) $(FILES)

jar:	$(FILES)
	cd .classes ; \
	jar cmf ../MANIFEST grader.jar *.class $(MODULES) ; \
	chmod a+x grader.jar ; \
	mv grader.jar ..

clean:
	rm -rf .classes/* grader.jar
        