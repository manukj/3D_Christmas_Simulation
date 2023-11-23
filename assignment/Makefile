
# Define the target directory
TARGET_DIR := ./

# Find all Java source files
JAVA_FILES := $(shell find $(TARGET_DIR) -name "*.java")

# Convert Java source files to class files
CLASS_FILES := $(patsubst %.java, %.class, $(JAVA_FILES))

# Define the target for cleaning
.PHONY: clean

# Default target
all: $(CLASS_FILES)

# Rule to compile Java source files to class files
%.class: %.java
	javac $<

# Clean rule to remove all class files
clean:
	find $(TARGET_DIR) -name "*.class" -exec rm -f {} +


run: clean
	javac Assignment.java
	java Assignment
