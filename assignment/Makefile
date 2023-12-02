
.PHONY: clean

# This target cleans the built classes.
clean:
	find ./ -name "*.class" -exec rm -f {} +

# This target cleans all built class, then compiles and runs Aliens Program.
run: clean
	javac Aliens.java
	java Aliens
