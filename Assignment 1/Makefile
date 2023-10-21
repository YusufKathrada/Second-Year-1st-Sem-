# Yusuf Kathrada
# Assignment 1
# Makefile

JAVAC=/usr/bin/javac
JAVA=/usr/bin/java
.SUFFIXES: .java .class

SRCDIR=src
BINDIR=bin
DOCDIR=docs

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) $(JFLAGS) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES=Vaccine.class VaccineArray.class VaccineArrayApp.class BinaryTreeNode.class BTQueueNode.class BTQueue.class BinaryTree.class BinarySearchTree.class Entry.class VaccineBSTApp.class
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

run1: $(CLASS_FILES)
	$(JAVA) -cp $(BINDIR) VaccineArrayApp

run2: $(CLASS_FILES)
	$(JAVA) -cp $(BINDIR) VaccineBSTApp

docs:
	javadoc -d $(DOCDIR) $(SRCDIR)/*.java
clean:
	rm $(BINDIR)/*.class
	
