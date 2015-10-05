JARNAME=tes
ENTRYPOINT=io.github.cnquiz.Main

all:
	(cd src; for f in `find . -name \*.java`; do echo $$f; javac $$f || exit $$?; done)
	(cd src; jar cvfe ../../bin/$(JARNAME).jar $(ENTRYPOINT) `find . -name \*.class -o -name \*.java`)

clean:
	$(RM) $(JARNAME).jar `find . -name \*.class`

run: all
	java -jar ../bin/$(JARNAME).jar

