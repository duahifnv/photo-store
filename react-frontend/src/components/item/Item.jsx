import '../../styles/item/item.css';

export const Item = (props) => {
  console.log(props.itemProps);
  return (
      <div id="item-wrapper">
          <div id="item-img">
              <img src={props.imgSrc} alt={props.imgAlt}/>
          </div>
          <div id="item-info">
              <p id="item-name">{props.label}</p>
              <div id="item-props">
                  {Object.entries(props.itemProps).map(([propKey, propValue]) => {
                      return <p><b>{propKey}:</b> <code>{propValue}</code></p>
                  })}
              </div>
              <div id="item-price">
                  <p id="current-price">{props.price}</p>
              </div>
          </div>
      </div>
  );
}