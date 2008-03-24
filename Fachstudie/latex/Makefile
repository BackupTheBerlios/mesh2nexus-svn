
PDFLATEX=pdflatex
BIBTEX=bibtex
MAKEINDEX=makeindex
GLOSSSTYLE=nomencl.ist
SRCS=$(shell echo *.tex)
IMAGES_DIR=./images
IMAGES=$(shell echo $(IMAGES_DIR)/*.jpg $(IMAGES_DIR)/*.png)
TARGET=mesh

all: $(TARGET).pdf

$(TARGET).pdf: $(SRCS) $(IMAGES) $(NOMENCLSTYLE)
	$(PDFLATEX) $(TARGET).tex
	$(BIBTEX) $(TARGET)
	$(MAKEINDEX) $(TARGET).glo -s $(GLOSSSTYLE) -o $(TARGET).gls
	$(PDFLATEX) $(TARGET).tex
	$(PDFLATEX) $(TARGET).tex

clean:
	rm -f *.aux *.out *.toc *.lof *.log *.glo *.gls *.bbl *.blg *.ilg\
		$(TARGET).pdf
