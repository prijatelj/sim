Sim: Persona Simulator
=
Sim, in this current state, is a simple and rough expert system that when given the proper input and output files for the Neural net, will answer questions simply and statically.

Concept
-
Spring 2016:

Sim utilizes natural language processing and hand coded descion trees that filter the user's input. This filtered input is then passed to a layered hierarchy of nerual networks, where each layer handles different parts of human communication and response. Through proper training, the neural nets will return an acceptable and expected answer based on a character who has the character traits specified in the system's personality files.

Sim was initially conceptualized by Derek S. Prijatelj and refined with the help of Timothy Ireland in the Spring of 2016 as a semester project for their Introduction to Artifical Intelligence course at Duquesne University. At the time it was only a very simple question answer system with a very limited knowledge base of one or two of the class's chapters.

Now, Sim is Derek's undergraduate research project and he will continue development primarially on his own.

Sim is aiming high in the artificial intelligence world; high enough to be considered touching upon the "Holy Grail" of AI. Some functions that are expected in Sim is the ability to recall previous subjects and data from those subjects in conversation, as well as be modular in design so that parts can be turned on, off, or switched out entirely, specifically with the personality files. Obviously small talk at a higher level than modern chatter bots is expected to be achieved with Sim.

The ideal Sim is obviously ambitious and probably too much for one person to accomplish alone. I (Derek) recognize this and after I have achieved some satisfactory level of success in starting the project, will release it to the public in hopes others will contribute. But for now, I will continue this project as means of study. We'll see where this goes. In the meantime this show cases the initial project which is no where near the ideal.

Goals
-
Current Goals:
+ Transfer all current Java files to C and C++ files.
+ Plan out the structure and processes that need to be taken care of to accomplish a realistic Persona, ie. static & variable pysche.
    - map out entire framework.
+ Set up a skeleton framework of Sim with modularity and the user's experience in mind. (this user being those who implement Sim)
+ Construct the "Listening" portion of Sim; input and the NLP front end.
    - properly setup `converse()` method of a Persona object.
    - incorporate SyntaxNet and its respective Parsey McParseface for syntax recognition. We need to know the type of sentence, have accurate POST, and be able to determine at least the Subject and Predicate of the sentence.
+ Refine existing system for a better Question and Answer System

Long Term Goals:
+ Acheive subject recognition and properly handle context within scope of conversation.
+ Achieve a basic persona
+ Enable Sim to Argue and Reason
+ Have Sim be able to be implemented in various platforms as an external application that is combined with other existing projects, such as Video Games, virtual tech support bots, and websites.
___
Sim &copy; 2016 Derek S. Prijatelj
___

Link Dump (in no particular order; read at your own risk)
-

* https://opennlp.apache.org/
* http://mallet.cs.umass.edu/
* http://jgibblda.sourceforge.net/#3._How_to_Program_with_JGibbLDA
* https://www.quora.com/What-libraries-exist-for-text-recognition-in-Java
* http://www.gamasutra.com/view/feature/134675/beyond_fa%C3%A7ade_pattern_matching_.php
* https://www.chatbots.org/
* http://mt-archive.info/AJCL-1980-McCord.pdf
* https://www.quora.com/topic/Conversational-Agents-chatbots
* https://www.quora.com/What-is-the-best-way-to-learn-and-write-a-AI-Chat-bot
* https://en.wikipedia.org/wiki/Support_vector_machine
* https://en.wikipedia.org/wiki/Naive_Bayes_classifier
* https://en.wikipedia.org/wiki/Levenshtein_distance
* https://en.wikipedia.org/wiki/Stemming
* https://en.wikipedia.org/wiki/Needleman%E2%80%93Wunsch_algorithm
* https://en.wikipedia.org/wiki/Jaro%E2%80%93Winkler_distance
* http://stackoverflow.com/questions/10220790/ai-string-text-classification-categorization-e-g-a-string-text-is-classified
* http://www.jmlr.org/papers/volume2/lodhi02a/lodhi02a.pdf
* https://en.wikipedia.org/wiki/String_kernel
* https://en.wikipedia.org/wiki/Multilayer_perceptron 
* https://en.wikipedia.org/wiki/Perceptron
* https://en.wikipedia.org/wiki/Backpropagation
* http://theory.stanford.edu/~aiken/publications/papers/sigmod03.pdf
* http://cs231n.github.io/neural-networks-1/#reg
* http://cs231n.github.io/neural-networks-2/#reg
