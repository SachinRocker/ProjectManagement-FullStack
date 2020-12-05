import logo from './logo.svg';
import './App.css';
import Dashboard from "./components/Dashboard"
import Header from "./components/Layout/Header"
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
function App() {
  return (
    <div className="App">
    <Header/>
    <Dashboard/>
     
    </div>
  );
}

export default App;
