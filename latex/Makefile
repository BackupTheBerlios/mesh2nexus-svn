
PDFLATEX=pdflatex
SRCS=mesh.tex Hardware.tex PCs+WLAN-Karten.tex Zusammenfassung.tex\
     PCI-WLAN-Karten.tex Mini-PCI_WLAN-Karten.tex PCIMCIA_WLAN-Karten.tex\
     WLAN-Router.tex PDAs_Handys.tex
IMAGES_DIR=./images
IMAGES=$(IMAGES_DIR)/WMN.jpg
TARGET=mesh

all: $(TARGET).pdf

$(TARGET).pdf: $(SRCS) $(IMAGES)
	$(PDFLATEX) $(TARGET).tex
	$(PDFLATEX) $(TARGET).tex

clean:
	rm -f *.aux *.out *.toc *.lof *.log $(TARGET).pdf
