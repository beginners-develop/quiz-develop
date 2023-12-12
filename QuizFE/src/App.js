import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import routes from "./Routes";

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          {routes.map((route, index) => {
            const Page = route.component;
            return <Route key={index} path={route.path} element={<Page />} />;
          })}
        </Routes>
      </div>
    </Router>
  );
}

export default App;
