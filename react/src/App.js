import { BrowserRouter, Route, Routes } from "react-router-dom";
import cookie from "react-cookies";
import "./App.css";
import Header from "./layout/Header";
import Home from "./component/Home";
import Detail from "./component/Detail";
import LogIn from "./component/LogIn";
import Register from "./component/Register";
import MyUserReducer from "./reducers/MyUserReducer";
import { createContext, useReducer } from "react";
import Admin from "./role/Admin";
import Seller from "./role/Seller";
import User from "./role/User";
import CreateRes from "./component/CreateRes";
import Cart from "./component/Cart";
import MyCartCounterReducer from "./reducers/MyCartCounterReducer";

export const MyUserContext = createContext();
export const MyCartContext = createContext();

const countCart = () => {
  let cart = cookie.load("cart") || null;
  if (cart !== null)
    return Object.values(cart).reduce(
      (init, current) => init + current["quantity"],
      0
    );
  return 0;
};

const App = () => {
  const [user, dispatch] = useReducer(
    MyUserReducer,
    cookie.load("user") || null
  );
  const [cartCounter, cartDispatch] = useReducer(
    MyCartCounterReducer,
    countCart()
  );

  return (
    <MyUserContext.Provider value={[user, dispatch]}>
      <MyCartContext.Provider value={[cartCounter, cartDispatch]}>
        <BrowserRouter className="App">
          <Header />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/restaurant/:restaurantId" element={<Detail />} />
            <Route path="/login" element={<LogIn />} />
            <Route path="/register" element={<Register />} />
            <Route path="/restaurant" element={<CreateRes />} />
            <Route path="/cart" element={<Cart />} />

            <Route path="/admin" element={<Admin />} />
            <Route path="/seller" element={<Seller />} />
            <Route path="/user" element={<User />} />
          </Routes>
        </BrowserRouter>
      </MyCartContext.Provider>
    </MyUserContext.Provider>
  );
};

export default App;
