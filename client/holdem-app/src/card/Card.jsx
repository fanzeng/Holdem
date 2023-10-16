export function Card({name}) {
  const importImages = r => {
    let images = {};
    r.keys().forEach((item) => { images[item.replace('./', '')] = r(item); });
    return images;
  }
  const deckImages = importImages(require.context('../resource/deck/', false, /\.(png|jpe?g|svg)$/));
  return <>
    <div className="center-flex">
    <span className="card-span">&nbsp;</span>
    <img className="card-image" src={deckImages[`${name ? name.replace('T', '10') : 'red_back'}.png`]} alt="" />
    </div>
  </>
}