## Voici un Makefile
## Pour plus d'information sut un Makefile voir
## https://makefiletutorial.com/

INSTALLDIR=out/production/TP1
MAINCLASS=Main
#MAINCLASS=TestTryCatch
JARFILE=2SAT

JDKBIN=$(shell dirname `which java`)
#JDKBIN=/usr/bin
#JDKBIN=/usr/local/opt/openjdk/bin
#ECLIPSE_LOC=/Applications/Eclipse\ Java.app
#JDKBIN=$(ECLIPSE_LOC)/Contents/Eclipse/plugins/org.eclipse.justj.openjdk.hotspot.jre.full.macosx.x86_64_16.0.2.v20210721-1149/jre/bin
ECLIPSE_LOC=/opt/eclipse-jee-2021-03
#JDKBIN=$(ECLIPSE_LOC)/plugins/org.eclipse.justj.openjdk.hotspot.jre.full.linux.x86_64_15.0.2.v20210201-0955/jre/bin
JAVAC=$(JDKBIN)/javac
JAR=$(JDKBIN)/jar
JAVA=$(JDKBIN)/java

all: compile jar execjar

# Cible (target, en anglais)  pour compiler
compile:
	cd src ; make JAVAC="$(JAVAC)" INSTALLDIR="$(INSTALLDIR)" MAINCLASS="$(MAINCLASS)" compile

install:
	cd src ; make install

jar: compile
	cd $(INSTALLDIR); \
	echo Main-Class: $(subst /,.,$(MAINCLASS)) > manifest.txt ; \
	$(JAR) cvfm $(JARFILE).jar manifest.txt ./
	mv $(INSTALLDIR)/$(JARFILE).jar ./

clean:
	cd src ; make clean ; make INSTALLDIR="$(INSTALLDIR)" cleanInstall
	rm *.zip *.jar manifest.*


# Cible pour executer 
exec:
	$(JAVA) -classpath $(INSTALLDIR) $(MAINCLASS)

execjar: $(JARFILE).jar
	$(JAVA) -jar $(JARFILE).jar

version:
	$(JAVA) --version

# Executer automatiquent les test
test: #compile jar
	for file in `ls formulas/testSet0/*`; do \
	$(JAVA) -jar $(JARFILE).jar $$file ; done ; \
	for file in `ls formulas/testSet1/*`; do \
	$(JAVA) -jar $(JARFILE).jar $$file ; done \

testIntoResults:
	make test > results.txt

# Cible pour créer son rendu de tp 
zip:
	moi=$$(whoami) ; zip -r $${moi}_renduTP1.zip *


# Cible pour vérifier le contenu de son rendu de tp 
zipVerify:
	moi=$$(whoami) ; unzip -l $${moi}_renduTP1.zip

#  LocalWords:  SAT
