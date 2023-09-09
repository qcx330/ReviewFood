import { useContext, useEffect, useState } from "react";
import Apis, { endpoints } from "../configs/Apis";
import "./MenuItem.css";
import { MyCartContext } from "../App";
import cookie from "react-cookies";

const MenuItem = (props) => {
  const [, cartDispatch] = useContext(MyCartContext);
  const [item, setItem] = useState([]);
  useEffect(() => {
    const loadItem = async () => {
      let { data } = await Apis.get(endpoints["menu-item"](props.id));
      setItem(data);
    };

    loadItem();
  }, []);

  const order = (item) => {
    cartDispatch({
        "type": "inc",
        "payload": 1
    });
    
    // lưu vào cookies
    let cart = cookie.load("cart") || null;
    if (cart == null)
        cart = {};
    
    if (item.id in cart) { // sản phẩm có trong giỏ
        cart[item.id]["quantity"] += 1;
    } else { // sản phẩm chưa có trong giỏ
        cart[item.id] = {
            "id": item.id,
            "name": item.name,
            "quantity": 1,
            "unitPrice": item.price
        }
    }

    cookie.save("cart", cart);

    console.info(cart);
}

  return (
    <>
      {item &&
        item.map((item) => {
          return (
            <>
              <div className="row product" key={item.id}>
                <div className="col-md-2">
                  <img src={item.image} alt="Sample Image" height="150" />
                </div>
                <div className="col-md-6 product-detail">
                  <h5>{item.name}</h5>
                  <p>{item.description}</p>
                </div>
                <div className="col-md-2 product-price">{item.price} VNĐ</div>
                <div className="col-md-2 product-button">
                  <button id="order-button" onClick={() => order(item)}>Đặt món</button>
                </div>
              </div>
            </>
          );
        })}
    </>
  );
};

export default MenuItem;
