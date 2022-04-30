import "./App.css";
import Dashboard from "./component/Dashboard";
import Header from "./component/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddProject from "./component/Project/AddProject";
import { Provider } from "react-redux";
import store from "./store";
import React from "react";
import UpdateProject from "./component/Project/UpdateProject";
import ProjectBoard from "./component/ProjectBoard/ProjectBoard";
import AddProjectTask from "./component/ProjectBoard/ProjectTask/AddProjectTask";
import UpdateProjectTask from "./component/ProjectBoard/ProjectTask/UpdateProjectTask";
import Landing from "./component/Layout/Landing";
import Register from "./component/UserManagement/Register";
import Login from "./component/UserManagement/Login";
import jwt_decode from "jwt-decode";
import setJWTToken from "./securityUtils/setJWTToken";
import { SET_CURRENT_USER } from "./actions/types";
import { logout } from "./actions/securityActions";

const jwtToken = localStorage.jwtToken;

if (jwtToken) {
  setJWTToken(jwtToken);
  const decoded_jwtToken = jwt_decode(jwtToken);
  store.dispatch({
    type: SET_CURRENT_USER,
    payload: decoded_jwtToken,
  });

  const currentTime = Date.now() / 1000;
  if (decoded_jwtToken.exp < currentTime) {
    store.dispatch(logout());
    window.location.href = "/";
  }
}

class App extends React.Component {
  render() {
    return (
      <Provider store={store}>
        <Router>
          <div className="App">
            <Header />
            {
              // PUBLIC ROUTES
            }
            <Route exact path="/" component={Landing} />
            <Route exact path="/register" component={Register} />
            <Route exact path="/login" component={Login} />

            {
              // PRIVATE ROUTES
            }
            <Route exact path="/dashboard" component={Dashboard} />
            <Route exact path="/addProject" component={AddProject} />
            <Route exact path="/updateProject/:id" component={UpdateProject} />
            <Route exact path="/projectBoard/:id" component={ProjectBoard} />
            <Route
              exact
              path="/addProjectTask/:id"
              component={AddProjectTask}
            />
            <Route
              exact
              path="/updateProjectTask/:backlog_id/:pt_id"
              component={UpdateProjectTask}
            />
          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;
