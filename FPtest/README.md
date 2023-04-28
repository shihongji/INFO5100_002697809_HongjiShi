# Final Project
## Requirements
### Basic Components for Final Project
- Use JavaFX for GUI
- Include class diagram of your project in the submission #todo 
- There must be use of inheritance, encapsulation and interfaces
- There must be at least one design pattern #todo 
- Code must be well commented
- Include screenshots of various tests done by developer
- Have appropriate exception handling
- Add source code to GitHub account and send link with submission
### Option 1. Image Management Tool
- Take one or more image file(s) as input
	- Develop GUI to allow user to upload image file(s) from desktop/laptop
	- When unloaded, show image(s) thumbnail(s) (100\*100) to users on GUI
	- Also, show image properties (height, width, camera, location, etc.)
- Use Image IO / ImageMagick / JMagick to convert image to various formats
	- Develop GUI to allow user to select which format(s) to convert the image(s) to
	- Develop GUI to allow user to download converted images
 - Optional (no extra credits): Allow user to apply various filters (color tints, black-and-white, etc.) to images(s)
## Explaination:
### Design pattern
Consider using the Strategy pattern for different image processing operations. This would define a common interface for all operations and then implement each operation in a separate class. This makes it easy to add new operations or modify existing ones without affecting the rest of the code.
