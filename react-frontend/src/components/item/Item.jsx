import '../../styles/item/item.css';

export const Item = (props) => {
  return (
      <div id="item-wrapper">
          <div id="item-img">
              <img src={props.imgSrc} alt={props.imgAlt}/>
          </div>
          <div id="item-info">
              <p id="item-name">{props.label}</p>
              <div id="item-props">
                  {props.itemProps.map((prop) => {
                      return <p>{prop}</p>
                  })}
              </div>
              <div id="item-price">
                  {props.prevPrice && (
                      <p id="previous-price"><s>{props.prevPrice}</s></p>
                  )}
                  <p id="current-price">{props.price}</p>
              </div>
          </div>
      </div>
  );
}