
PDFLATEX=pdflatex
SRCS=mesh.tex Hardware.tex PCs+WLAN-Karten.tex Zusammenfassung.tex\
     PCI-WLAN-Karten.tex Mini-PCI_WLAN-Karten.tex PCMCIA_WLAN-Karten.tex\
     WLAN-Router.tex SoHo-Router.tex Professionelle_Router.tex\
     PDAs_Handys.tex
IMAGES_DIR=./images
IMAGES=$(IMAGES_DIR)/WMN.jpg $(IMAGES_DIR)/Linksys_WMP55AG.jpg\
       $(IMAGES_DIR)/Asus_WL500G.jpg $(IMAGES_DIR)/DLink_DWLA520.jpg\
       $(IMAGES_DIR)/SMC_2536WAG.jpg $(IMAGES_DIR)/Linksys_WPC55AG.jpg\
       $(IMAGES_DIR)/Linksys_WRT54G.jpg $(IMAGES_DIR)/Gigabyte_GNWPEAG.jpg\
       $(IMAGES_DIR)/Linksys_WRT55AG.jpg $(IMAGES_DIR)/Intel_2915ABG.jpg\
       $(IMAGES_DIR)/Intel_4965AGN.jpg $(IMAGES_DIR)/Intel_3945ABG.jpg\
       $(IMAGES_DIR)/Netgear_WAG311.jpg $(IMAGES_DIR)/Netgear_WAG511.jpg\
       $(IMAGES_DIR)/Proxim_Orinoco_Gold_8480WD.jpg\
       $(IMAGES_DIR)/Wistron_CM9.jpg
TARGET=mesh

all: $(TARGET).pdf

$(TARGET).pdf: $(SRCS) $(IMAGES)
	$(PDFLATEX) $(TARGET).tex
	$(PDFLATEX) $(TARGET).tex

clean:
	rm -f *.aux *.out *.toc *.lof *.log $(TARGET).pdf
