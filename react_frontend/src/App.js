import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import SearchBar from './Components/SearchBar';
import MovieData from './Data.json';


function App() {
  return (
    <div className="App">
      <SearchBar data={MovieData}/>
    </div>
  );
}

export default App;
