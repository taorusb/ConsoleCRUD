### Console CRUD application v 1.0
##### JDK version used: 15
###### To launch the application, type in the command prompt:

javac -sourcepath ./src -d bin src/com/consolecrud/Main.java

java -classpath ./bin com.consolecrud.Main


The request command has the following structure (commands are processed regardless of the case of letters):

[operation type] [model type] [arguments]

where operation type:

show
add
update
delete

model type:

writer
post
label

arguments for some model [show] operation:

witer [all]
post [writerid=]
label [postid=]

arguments for some model [add] operation:

writer [firstname= lastname=]
post [writerid= content=]
label [postid= name=]

arguments for some model [update] operation:

writer [id= firstname= lastname=]
post [id= content=]
label [id= name=]

arguments for some model [update] operation:

writer [id=]
post [id=]
label [id=]