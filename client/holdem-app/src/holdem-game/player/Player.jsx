import { useEffect, useState } from "react";
import { Card } from "../../card/Card";
import "./Player.css";

export function Player({ id, enable, onPlayerBet, privateCards, stackValue, setStackValue, currentBet }) {
  const [betValue, setBetValue] = useState(0);
  const [callBtnJustClicked, setCallBtnJustClicked] = useState(false);
  const [raiseBtnJustClicked, setRaiseBtnJustClicked] = useState(false);
  const [checkFoldBtnJustClicked, setCheckFoldBtnJustClicked] = useState(false);

  const importImages = r => {
    let images = {};
    r.keys().forEach((item) => { images[item.replace('./', '')] = r(item); });
    return images;
  }
  const chipImages = importImages(require.context('../../resource/chip/', false, /\.(png|jpe?g|svg)$/));

  const bb = 10;
  const onChipPlusBtnClick = () => {
    if (stackValue - bb >= 0) {
      setBetValue(betValue + bb);
      console.log(betValue)
    }
  }
  const onChipMinusBtnClick = () => {
    if (betValue - bb >= 0) {
      setBetValue(betValue - bb);
      console.log(betValue)
    }
  }
  function onCallBtnClick() {
    if (!callBtnJustClicked) {
      setCallBtnJustClicked(true);
      setTimeout(() => {
        setCallBtnJustClicked(false);
      }, 200);
      setStackValue(stackValue - betValue);
      onPlayerBet(id, currentBet);
    }
  }

  function onRaiseBtnClick() {
    if (!raiseBtnJustClicked) {
      setRaiseBtnJustClicked(true);
      setTimeout(() => {
        setRaiseBtnJustClicked(false);
      }, 200);
      setStackValue(stackValue - betValue);
      onPlayerBet(id, betValue);
    }
  }

  function onCheckFoldBtnClick() {
    if (!checkFoldBtnJustClicked) {
      setCheckFoldBtnJustClicked(true);
      setTimeout(() => {
        setCheckFoldBtnJustClicked(false);
      }, 200);
      setStackValue(stackValue - betValue);
      onPlayerBet(id, 0, currentBet > 0)
    }
  }

  useEffect(() => {
    console.log('currentBet =', currentBet)
    setBetValue(currentBet ?? 0);
  }, [currentBet]);

  return <>
    <div className="center-flex">
      <div className={enable ? '' : 'widget-disabled'}>
        <span className="label-text">Player {parseInt(id) + 1} &nbsp;</span>
        <span className="label-text">Stack: {stackValue}&nbsp;</span>
        <div>
          <button onClick={onChipMinusBtnClick}><img src={chipImages[`gambling-chip.png`]} alt="" width="25px" height="25px" />-</button>
          <span className="label-text">{betValue}</span>
          <button onClick={onChipPlusBtnClick}><img src={chipImages[`gambling-chip.png`]} alt="" width="25px" height="25px" />+</button>
          <button className={betValue > currentBet ? '' : 'widget-disabled'} onClick={onRaiseBtnClick}>Raise&nbsp;</button>
        </div>
        <button className={betValue === currentBet && currentBet > 0 ? '' : 'widget-disabled'} onClick={onCallBtnClick}>Call&nbsp;</button>
        <button className={betValue === 0 ? '' : 'widget-disabled'} onClick={onCheckFoldBtnClick}>{currentBet > 0 ? 'Fold' : 'Check'}</button>

        <div className="center-flex">
          <Card key="0" name={privateCards[0]} />
          <Card key="1" name={privateCards[1]} />
        </div>
      </div>
    </div>
  </>
}