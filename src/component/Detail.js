import { useState, useEffect, useContext } from "react";
import {
  Card,
  Tab,
  Tabs,
  Form,
  Button,
  ListGroup,
  Alert,
} from "react-bootstrap";
import { Link, useParams } from "react-router-dom";
import MySpinner from "../layout/MySpinner";
import "./Detail.css";
import { MyCartContext, MyUserContext } from "../App";
import Apis, { authApi, endpoints } from "../configs/Apis";
import MenuItem from "./MenuItem";
import Moment from "react-moment";
import avatar from "../images/blank_avatar.png";

const Detail = () => {
  const [user] = useContext(MyUserContext);
  const [cartCounter] = useContext(MyCartContext);
  const { restaurantId } = useParams();
  const [res, setRes] = useState(null);
  const [menu, setMenu] = useState(null);
  const [comments, setComments] = useState([]);
  const [content, setContent] = useState();
  const [isFollowing, setIsFollowing] = useState('');

  useEffect(() => {
    const loadRestaurant = async () => {
      let { data } = await Apis.get(endpoints["details"](restaurantId));
      setRes(data);
    };

    const loadMenu = async () => {
      let { data } = await Apis.get(endpoints["menu"](restaurantId));
      setMenu(data);
    };

    const loadComments = async () => {
      let { data } = await Apis.get(endpoints["comments"](restaurantId));
      setComments(data);
      console.log(data)
    };

    loadRestaurant();
    loadMenu();
    loadComments();
  }, []);

  const addComment = () => {
    const process = async () => {
      let { data } = await authApi().post(endpoints["add-comment"], {
        commentText: content,
        restaurantId : res,
      });
      setComments([...comments, data]);
      console.log(comments);
    };

    process();
  };

  const follow = () => {
    const process = async () => {
      if (isFollowing !== "Following") {
        let response = await authApi().post(endpoints["follow"], res);
        // setIsFollowing(response.data);
        if (response.status === 201) {
          setIsFollowing("Following");
        }
        else if (response.status === 200) {
          console.log(response);
          setIsFollowing("Following");
        }
      } else {
        let response = await authApi().delete(endpoints["unfollow"], {data : res});
        if (response.status === 204)
          setIsFollowing("Follow");
        else console.log(restaurantId);
      }

      // }

    };
    process();
  };
  if (res === null) return <MySpinner />;

  let url = `/login?next=/restaurant/${restaurantId}`;
  return (
    <>
      <Card className="bg-light text-white" id="intro-res">
        <Card.Img className="img" src={res.image} alt={res.name} />
        <Card.ImgOverlay className="restaurant-overlay">
          <Card.Title className="text-overlay">{res.name}</Card.Title>
          <Card.Text className="text-overlay">{res.address}</Card.Text>
          <button id="order-button" onClick={follow} onChange={follow}>
            {isFollowing === "" ? "Follow" : isFollowing}
          </button>
          <button id="order-button">Nhắn tin</button>
        </Card.ImgOverlay>
      </Card>
      <div className="restaurant-content">
        <Tabs
          defaultActiveKey="menu"
          id="justify-tab-example"
          className="mb-3"
          justify
        >
          <Tab eventKey="menu" title="Menu">
            {menu &&
              menu.map((menu) => {
                return (
                  <div className="content-menu" key={menu.id}>
                    <div>
                      <h4 className="menu-name" >
                        {menu.name}
                      </h4>
                      <MenuItem id={menu.id} />
                    </div>
                  </div>
                );
              })}
          </Tab>
          <Tab eventKey="comment" title="Bình luận">
            {
              comments.length === 0 ? (
                <Alert variant="info" className="mt-1">
                  Chưa có bình luận nào!
                </Alert>
              ) : <>

              </>
            }
            {user === null ? (
              <p>
                Vui lòng <Link to={url}>đăng nhập</Link> để bình luận!{" "}
              </p>
            ) : (
              <>
                <Form.Control
                  as="textarea"
                  aria-label="With textarea"
                  value={content}
                  onChange={(e) => setContent(e.target.value)}
                  placeholder="Nội dung bình luận"
                />
                <Button onClick={addComment} className="mt-2" variant="info">
                  Bình luận
                </Button>
              </>
            )}
            <hr />
            <ListGroup>
              {comments &&
                comments.map((c) => (
                  <ListGroup.Item id={c.id}>
                    <div className="user-comment">
                      <img
                        className="thumbnail-image"
                        src={c.userId.avatar === null ? avatar : c.userId.avatar}
                        roundedCircle
                        width="45"
                        height="35"
                        alt="user"
                      />
                      {c.userId.lastName} {c.userId.firstName}
                    </div>
                    <div className="content-comment">
                      <div className="created-dated">
                        <Moment locale="vi" fromNow>
                          {c.createdDate}
                        </Moment>
                      </div>
                      {c.commentText}
                    </div>
                  </ListGroup.Item>
                ))}
            </ListGroup>
          </Tab>
          <Tab eventKey="map" title="Bản đồ">
            Bản đồ
          </Tab>
        </Tabs>
      </div>
      <div class="shopping-cart" data-product-count={cartCounter}>
        <Link to="/cart">
          <span class="cart-icon">&#128722;</span>
        </Link>
      </div>
    </>
  );
};

export default Detail;
