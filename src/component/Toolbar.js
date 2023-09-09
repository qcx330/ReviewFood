import "./Toolbar.css";
import arrow from "../images/arrow.png";
import news from "../images/news.png";

const Toolbar = () => {
  return (
    <>
      <div>
        <div className="toolbar-left">
        <div className="header">
        <img src={news} width="30" height="30" alt=""/>
        <label>Khám phá</label>
        </div>
           <ul className="list-toolbar">
            <li> 
            <span>Mới nhất</span>
            <img src={arrow} width="35" height="35" alt=""/>
            </li>
            <li> 
            <span>Sưu tập</span>
            <img src={arrow} width="35" height="35" alt=""/>
            </li>
            <li> 
            <span>Bình luận</span>
            <img src={arrow} width="35" height="35" alt=""/>
            </li>
            <li> 
            <span>Khuyến mãi</span>
            <img src={arrow} width="35" height="35" alt=""/>
            </li>
          </ul>
        </div>
      </div>
    </>
  );
};

export default Toolbar;
