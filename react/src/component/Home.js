import Restaurant from "./Restaurant";
import Toolbar from "./Toolbar";
import "./Home.css";

const Home = () => {

  return (
    <>
      <div className="content-home">
        <Toolbar className="toolbar-left" />
        <Restaurant className="list-dish"/>
      </div>
    </>
  );
};

export default Home;
