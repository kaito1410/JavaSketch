Java Sketch
By: Steven Zhu


Program Description:
make and then make run to run program


Menubar:
	
File-New: done
	
File-Load: done
	
File-Save: done
	
View-Full Size: done
	


Tool Palette - all working except:
	***Random bug*** sometimes JToggleButton does not Toggle properlly and the old button does not get untoggled
	
Fix: just toggle another button and it should work normally again

Color 
Palette - Uses JColorChooser, the preview panel is under the button

Thickness 
Palette - uses JSlider, can choose any thickness between 1-10
	***bug*** JSlider painting is somehow affected by increasing/decreasing thickness, not sure why JSlider painting is connected with WidthPanel painting.


Currently Selected Shapes are highlighted with a rectangle border, implemented in MyShape.java

Drawing 
Preview works - implemented in View.java

Moving Shapes work - implemented in View.java

Tool restrictions: when a line is selected, the fill tool becomes disabled

Tool Palette reuses code from PaintDemo in java_examples.zip
Overlapped shapes are handled FIFO where the most recent shape is selected in the case of an overlap

Painting overlapped shapes will paint oldest shape -> newest shape


OS - Ubuntu 16.04
JDK - 1.8.0_91


Enhancements:
N/A
