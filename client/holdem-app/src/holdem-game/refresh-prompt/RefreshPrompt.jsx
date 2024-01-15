export function RefreshPrompt({gameSessionStatus, retryCount}) {
  return <>
    <div style={{
      position: 'fixed',
      top: 0,
      left: 0,
      width: '100vw',
      height: '100vh',
      backgroundColor: '#333333',
      opacity: 0.5,
      zIndex: 0
    }}></div>
    <div style={{
      color: 'black',
      backgroundColor: 'beige',
      fontSize: 'large',
      padding: '15px',
      borderRadius: '5px',
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      margin: 0,
      position: 'absolute',
      top: '50%',
      left: '50%',
      transform: 'translate(-50%, -50%)'
    }}>
      <p>
        {gameSessionStatus}.&nbsp;
        {gameSessionStatus === 'Session uninitialised' ? 'Connecting ' + '...'.repeat(retryCount + 1) : <p>Please <a href='.'>Refresh</a> the page.</p> }
      </p>
    </div>
  </>
}