import { Card } from "../../card/Card"
export function Board({cardStage}) {
  return <>
    <div className="board center-flex">
      { cardStage === 'SHOW_DOWN' || cardStage === 'RIVER' || cardStage === 'TURN' || cardStage === 'FLOP' ? <Card key="0" name="10C"/> : <Card key="0" name="red_back"/> }
      { cardStage === 'SHOW_DOWN' || cardStage === 'RIVER' || cardStage === 'TURN' || cardStage === 'FLOP' ? <Card key="1" name="10S"/> : <Card key="1" name="red_back"/> }
      { cardStage === 'SHOW_DOWN' || cardStage === 'RIVER' || cardStage === 'TURN' || cardStage === 'FLOP' ? <Card key="2" name="2C"/> : <Card key="2" name="red_back"/> }
      { cardStage === 'SHOW_DOWN' || cardStage === 'RIVER' || cardStage === 'TURN' ? <Card key="3" name="4D"/> : <Card key="3" name="red_back"/> }
      { cardStage === 'SHOW_DOWN' || cardStage === 'RIVER' ? <Card key="4" name="5H"/> : <Card key="4" name="red_back"/> }
    </div>
  </>
}