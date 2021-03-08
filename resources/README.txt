In this Assignment, we implemented all three levels provided in the Assignment. The following
features were completed as requirements:

1) Level 1: Scrubbing
-The ability for a user to scrub by dragging a slider (or scrollbar) on the UI.
-The ability of your program being able to show the animation as the user scrubs.
-Your UI should keep pace with the user, regardless of how rapidly the scrubber is moved around,
for files at least as big as the samples we’ve given you.
-If the user lets go of the slider, the program may continue playing or have the user explicitly
start playing. That is, how this functionality interacts with the existing play/pause/rewind/loop
functionality is up to you.

2) Level 2: Add a new animation: rotation
-The ability to add a rotate animation in the input file, and changes to the file reader to
support such files.
-The continued ability to use the file format we gave you: this must be an extension, not a
replacement, of the existing file format.
-Demonstrate a shape that only rotates.
-Demonstrate a shape that simultaneously rotates and changes size, position, and/or color.
The ability to export the animation to an SVG file that includes rotation.

3) Level 3: Shapes in Layers
-The ability of an input file to take in layer information. If no layers are specified, all shapes
are assumed to be in the same layer. How the file will specify this is up to you.
-The ability of your program being able to successfully parse such an input file.
-The continued ability to use the file format we gave you: this must be an extension, not a
replacement, of the existing file format.
-The ability of your program to render correctly such a layered animation in its editor view.

At least one example of each of the following:
-an input file with no layering information (any of those provided to you by us earlier),
-an input file with two non-empty layers such that the shapes in different layers will overlap at
some point in the animation,
-an input file with at least three non-empty layers such that the shapes in different layers will
overlap at some point in the animation.

-The ability to add new layers, add shapes to a specific layer, delete layers (and all the shapes
in them), and to reorder layers.

The files that were affected from implementing these features were the AnimatorImpl, AttributeImpl,
AbstractShape, ChangeType, MotionImpl, and all the interfaces associated with these implementation
classes. In AttributeImpl, we created a builder that takes in the required parameters of x, y,
width, height, and color for an attribute(similar to a keyframe). Since angles are optional and
there could be potential for future animation additions, it was practical to use a builder that
can customize an attribute. Based on this fact, we added methods to the model in AnimatorImpl for
addKeyframe and editKeyframe that take in an Attribute object as a parameter. This way if the
requirements for keyframes change in the future, we wouldn't need to rewrite or add any more methods
since we have methods take in classes represented by the interface Attribute. This methods were also
added to Abstract Shape since the model delegates the working of adding an attribute to the Shape
class. We made AbstractAttribute so that classes could extend and add on to the abstract class that
contains required fields such as x, y and color. We added methods to the model that can add, delete,
and reorder layers based on input. We made changes to the AnimationReader and AnimationBuilder so
that files with angles and layers could be read properly while also maintaining reading older files.
The animation builder needed the ability to add layers and shapes to layers so this is why it was
modified. We modified the AnimationControllerImpl so that we could incorporate buttons for layers,
JList for layers, and a JSlider for scrubbing. We extended our editor view to add the capabilities
for layers, angles for rotations, and a slider for scrubbing. We decided to extend the editor view
so that we could minimize the amount of code we needed to rewrite while also still having access to
a functioning editor view.
