import {
  Alert,
  Container,
  Row,
  Col,
  Card,
  ListGroup,
  Button,
} from "react-bootstrap";
import { Link, useSearchParams } from "react-router-dom";
import "./Restaurant.css";
import { useEffect, useState } from "react";
import MySpinner from "../layout/MySpinner";
import Apis, { endpoints } from "../configs/Apis";

const Restaurant = () => {
  const [restaurants, setRestaurants] = useState([]);
  const [q] = useSearchParams();

  useEffect(() => {
    const loadRes = async () => {
      try {
        let e = endpoints["restaurants"];

        let cateId = q.get("cateResId");
        if (cateId !== null) e = `${e}?cateResId=${cateId}`;
        else {
          let kw = q.get("kw");
          if (kw !== null) e = `${e}?kw=${kw}`;
        }

        let res = await Apis.get(e);
        setRestaurants(res.data);
      } catch (ex) {
        console.error(ex);
      }
    };

    loadRes();
  }, [q]);

  if (restaurants === null) return <MySpinner />;

  if (restaurants.length === 0)
    return (
      <Alert variant="info" className="mt-1">
        Không có nhà hàng nào!
      </Alert>
    );

  return (
    <>
      <Container className="dish-container">
        <Row>
          {restaurants &&
            restaurants.map((res) => {
              return (
                <Col xs={12} md={3}>
                  <Card
                    style={{ width: "18rem" }}
                    key={res.id}
                    className="dish-card"
                  >
                    <Card.Img
                      variant="top"
                      width="200"
                      height="190"
                      src={res.image}
                    />
                    <Card.Body className="content-res">
                      <Card.Title className="name-res">
                        <Link to={`restaurant/${res.id}`}>{res.name}</Link>
                      </Card.Title>
                      <Card.Text className="address-res">
                        {res.address}
                      </Card.Text>
                    </Card.Body>
                    <ListGroup className="list-group-flush">
                      <ListGroup.Item className="comment">
                        Comment 1
                      </ListGroup.Item>
                      <ListGroup.Item className="comment">
                        Comment 2
                      </ListGroup.Item>
                      <ListGroup.Item className="comment">
                        Comment 3
                      </ListGroup.Item>
                    </ListGroup>
                    <Card.Body>
                      <Button variant="success" className="card-button">
                        Bình luận
                      </Button>
                      <Button variant="success" className="card-button-save">
                        Lưu
                      </Button>
                    </Card.Body>
                  </Card>
                </Col>
              );
            })}
        </Row>
      </Container>
    </>
  );
};

export default Restaurant;
