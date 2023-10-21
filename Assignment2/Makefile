# Yusuf Kathrada
# Assignment 2
# Makefile

JAVAC=/usr/bin/javac
JAVA=/usr/bin/java
.SUFFIXES: .java .class

SRCDIR=src
BINDIR=bin
DOCDIR=docs

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) $(JFLAGS) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES=Vaccine.class BinaryTreeNode.class BTQueueNode.class BTQueue.class BinaryTree.class AVLTree.class AVLExperiment.class
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

run: $(CLASS_FILES)
	$(JAVA) -cp $(BINDIR) AVLExperiment
	
docs:
	javadoc -d $(DOCDIR) $(SRCDIR)/*.java
clean:
	rm $(BINDIR)/*.class
	
