
PDFLATEX=pdflatex
SRCS=mesh.tex Mesh-Net.tex Zusammenfassung.tex
IMAGES_DIR=./images
IMAGES=$(IMAGES_DIR)/WMN.jpg
TARGET=mesh

all: $(TARGET).pdf

$(TARGET).pdf: $(SRCS) $(IMAGES)
	$(PDFLATEX) $(TARGET).tex

clean:
	rm -f *.aux *.out *.toc *.lof *.log $(TARGET).pdf
