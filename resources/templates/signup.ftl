<#-- @ftlvariable name="error" type="java.lang.String" -->
<#import "common/bootstrap.ftl" as b>

<@b.page>
  <div class="row">
    <div class="col-md-4 col-md-offset-4">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title">Sign Up</h3>
        </div>
        <div class="panel-body">
            <form action="/signup" method="POST">
              <#if error??>
                <p>${error}</p>
              </#if>

              <div class="form-group">
                <label for="userId">Username</label>
                <input type="text" name="userId" class="form-control" id="userId" placeholder="Username">
              </div>
              <div class="form-group">
                <label for="email">Email address</label>
                <input type="email" name="email" class="form-control" id="email" placeholder="Email">
              </div>
              <div class="form-group">
                <label for="displayName">Display name</label>
                <input type="text" name="displayName" class="form-control" id="displayName" placeholder="Display name">
              </div>
              <div class="form-group">
                <label for="password">Password</label>
                <input type="password" name="password" class="form-control" id="password" placeholder="Password">
              </div>
              <button type="submit" class="btn btn-default">Sign Up</button>
            </form>
        </div>
      </div>
    </div>
  </div>
</@b.page>

