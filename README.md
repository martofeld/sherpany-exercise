## Explanations

### Networking
- Networking is done using Retrofit which is base on OkHttp. After the request is done the response is parsed using Gson.
- For image download Glide was used since it light and it delivers as expected.

### Database
- Easy database management can be achieved by using Room. It handles insertions, deletions, updates and POJO to SQL parsing, leaving you with the simple task of writing select queries

### Async operations
- Using AsyncTasks for simple requirements such as interacting with DB or executing an HTTP request
- LiveData provides an easy way to handle async data by allowing the Dev to subscribe an Observer and be informed every time data changes.

### Paging
- Paging Arch Component integrates seamlessly with Room and LiveData allowing easy, maintainable code.

### View performance
- By using the Recycler View, old views can be recycled, ensuring that the UI stays simple and responsive.