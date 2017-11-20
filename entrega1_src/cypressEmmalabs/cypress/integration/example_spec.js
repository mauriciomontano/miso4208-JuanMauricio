describe('Los estudiantes under monkeys', function() {
    it('visits los estudiantes and survives monkeys', function() {
        cy.visit('https://emmalabsinternal.herokuapp.com');
        cy.wait(1000);
        randomEvent(10);
    })
})

function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min)) + min;
};

function randomClick(monkeysLeft) {
    var monkeysLeft = monkeysLeft;
    if(monkeysLeft > 0) {
        cy.get('a').then($links => {
            var randomLink = $links.get(getRandomInt(0, $links.length));
            if(!Cypress.Dom.isHidden(randomLink)) {
                cy.wrap(randomLink).click({force: true});
                monkeysLeft = monkeysLeft - 1;
            }
            setTimeout(randomClick, 1000, monkeysLeft);
        });
    }   
}

function randomEvent(cantidadEventos) {
    var cantidadEventos = cantidadEventos;
    if(cantidadEventos > 0) {
        var eventoAleatorio = getRandomInt(0, 4);
        switch(eventoAleatorio) {
            case 0:
                // Hacer click en un link al azar
                cy.get('a').then($links => {
                    var aleatorio = $links.get(getRandomInt(0, $links.length));
                    if(!Cypress.Dom.isHidden(aleatorio)) {
                        cy.wrap(aleatorio).click({force: true});
                        cantidadEventos = cantidadEventos - 1;
                    }
                    setTimeout(randomEvent, 1000, cantidadEventos);
                });
                break;
            case 1:
                // Llenar un campo de texto al azar
                cy.get('input').then($inputs => {
                    var aleatorio = $inputs.get(getRandomInt(0, $inputs.length));
                    if(!Cypress.Dom.isHidden(aleatorio)) {
                        cy.wrap(aleatorio).click({force: true}).type('!#%%/$(/()%&#$R!#$');
                        cantidadEventos = cantidadEventos - 1;
                    }
                    setTimeout(randomEvent, 1000, cantidadEventos);
                });
                break;
            case 2:
                // Seleccionar un combo al azar
                cy.get('select').then($selects => {
                    var aleatorio = $selects.get(getRandomInt(0, $selects.length));
                    if(!Cypress.Dom.isHidden(aleatorio)) {
                        var comboBox = cy.wrap(aleatorio);
                        comboBox.then($options => {
                            comboBox.select($options.get(0).value);
                        });
                        cantidadEventos = cantidadEventos - 1;
                    }
                    setTimeout(randomEvent, 1000, cantidadEventos);
                });
                break;
            case 3:
                // Hacer click en un botón al azar
                cy.get('button').then($buttons => {
                    var aleatorio = $buttons.get(getRandomInt(0, $buttons.length));
                    if(!Cypress.Dom.isHidden(aleatorio)) {
                        cy.wrap(aleatorio).click({force: true})
                        cantidadEventos = cantidadEventos - 1;
                    }
                    setTimeout(randomEvent, 1000, cantidadEventos);
                });
                break;
        }
    }
}