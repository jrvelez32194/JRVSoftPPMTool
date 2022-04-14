import logo from "./logo.svg";
import "./App.css";
import Dashboard from "./component/Dashboard";
import Header from "./component/Layout/Header";

function App() {
  return (
    <div className="App">
      <Header />
      <Dashboard />
    </div>
  );
}

export default App;
