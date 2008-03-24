
PDFLATEX=pdflatex
BIBTEX=bibtex
SRCS=*.tex
IMAGES_DIR=./images
IMAGES=$(IMAGES_DIR)/*.jpg $(IMAGES_DIR)/*.png
TARGET=mesh

all: $(TARGET).pdf

$(TARGET).pdf: $(SRCS) $(IMAGES)
	$(PDFLATEX) $(TARGET).tex
	$(BIBTEX) $(TARGET)
	$(PDFLATEX) $(TARGET).tex
	$(PDFLATEX) $(TARGET).tex

clean:
	rm -f *.aux *.out *.toc *.lof *.log $(TARGET).pdf
