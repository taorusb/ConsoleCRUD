### Console CRUD application v 1.0
##### JDK version used: 15
###### To launch the application, type in the command prompt:

javac -sourcepath ./src -d bin src/com/consolecrud/Main.java

java -classpath ./bin com.consolecrud.Main

To load data, enter the command: loadData. After each change in the app, the data is saved automatically.

The first line in all files is the id counter, which is incremented after each object is added.

To write data to storage files, the following recording formats are used, for all file types, the "$" sign is used.

For writer.txt:

1$SomeFristname$SomeLastname$1,2,3

where 1-id , SomeFirstname-firstname, SomeLastname-lastname, 1,2,3-id's of posts in post.txt.

For post.txt:

1$Some content$creationDate$updateDate$1,2,3

where 1-id of the post, SomeContent-content, creationDate - creation date (01/01/2021 record format), updateDate-last update, 1,2,3-id's of labels in label.txt.

For label.txt:

1$SomeLabel

where 1-label id,someLabel-label.

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