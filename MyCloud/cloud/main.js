
// Use Parse.Cloud.define to define as many cloud functions as you want.
// For example:
Parse.Cloud.define("hello", function(request, response) {
  response.success("Hello world!");
});

  Parse.Cloud.afterSave("Bids", function(request) {
  var pushQuery = new Parse.Query(Parse.Installation);

  Parse.Push.send({
        where: pushQuery,
        data: {
            alert: "New Bid Posted",
            sound: "default"
              }
        },{
        success: function(){
           response.success('true');
        },
        error: function (error) {
           response.error(error);
        }
      });
});