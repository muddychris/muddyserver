class _ccc {
 linkedinAuth(args) {
    return new Promise((resolve, reject) => {
      var email = "";
      if (!args)
        return reject({
          success: false,
          message: "Not a valid user",
        });

      if (args.code == null)
        return reject({
          success: false,
          message: "Not a valid user",
        });
      //get access token
      var token = args.code;
      var sessionId = args.session_id;
      console.log(token);
      var querystring = require("querystring");
      const https = require("https");
      SettingsManagement.getSettings({
        filter: { name: "redirectauth" },
      })
        .then((linkResponce) => {
          if (
            linkResponce == null ||
            linkResponce.data[0].value == "" ||
            linkResponce.data[0].value == null
          ) {
            reject("Redirect Url not setup");
          }

          let post_data = querystring.stringify({
            grant_type: "authorization_code",
            code: token,
            redirect_uri: linkResponce.data[0].value,
            client_id: "77nxjwew1gzgst",
            client_secret: "TTnNM9nt3smMSD4h",
          });
          var post_options = {
            host: "www.linkedin.com",
            path: "/oauth/v2/accessToken",
            method: "POST",
            headers: {
              "Content-Type": "application/x-www-form-urlencoded",
              "Content-Length": Buffer.byteLength(post_data),
            },
          };

          //get user email from linkedin
          var post_req = https.request(post_options, (res) => {
            res.setEncoding("utf8");
            res.on("data", (chunk) => {
              var access_token = JSON.parse(chunk).access_token;
              fetch(
                "https://api.linkedin.com/v2/emailAddress?q=members&projection=(elements*(handle~))",
                {
                  method: "get",
                  headers: {
                    Authorization: "Bearer " + access_token,
                  },
                }
              )
                .then((response) => {
                  return response.json();
                })
                .then(async (data) => {
                  // konsole.log("data : " + JSON.stringify(data));
                  email = data.elements[0]["handle~"].emailAddress;
                  // konsole.log("email : " + email.toString());
                  if (email != null && email != "") {
                    var args = { email_address: email, token: access_token };
                    await this.loginService(args)
                      .then((res) => {
                        resolve(res);
                      })
                      .catch((err) => {
                        // konsole.log("err : " + JSON.stringify(err));
                        reject({
                          success: false,
                          message: "User could not be created.",
                        });
                      });
                  } else {
                    // konsole.log("err : " + "Out Here");
                    reject({
                      success: false,
                      message: "User could not be created.",
                    });
                  }
                })
                .catch((err) => {
                  reject({
                    success: false,
                    message: err,
                  });
                });
            });
          });
          // post the data
          post_req.write(post_data);
          post_req.end();
        })
        .catch((err) => {
          return reject({
            success: false,
            message: err,
          });
        });
    }); //promise
  }
  }