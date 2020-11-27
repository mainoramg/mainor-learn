package com.nan.progra1.lab2;

import com.nan.progra1.lab2.models.shapes.Shape;

/**
 * Displays a menu to the user continuously with options to create the figures,
 * show their data and draw them.
 *
 * @author Wendy Ram&iacute;rez
 */
public class Menu {

    static final String MENU_TEXT = "1) Crear un cuadrado\n"
            + "2) Crear un rect\u00E1ngulo\n"
            + "3) Crear un tri\u00E1ngulo rect\u00E1ngulo\n"
            + "4) Crear un cubo\n"
            + "5) Crear una esfera\n"
            + "6) Crear un cilindro\n"
            + "7) Salir";

    static final int PANEL_MAX_WIDTH = 300;
    static final int PANEL_MAX_HEIGHT = 300;

    /**
     * Displays a menu to the user continuously and performs the necessary
     * actions for each user selection.
     */
    public static void showMenu() {

        boolean continueMenu = true;
        int option;

        while (continueMenu) {
            option = IOManager.requestInt(MENU_TEXT);
            Shape shape = null;

            switch (option) {
                case 1:
                    shape = ShapesController.createSquare();
                    break;
                case 2:
                    shape = ShapesController.createRectangle();
                    break;
                case 3:
                    shape = ShapesController.createRightTriangle();
                    break;
                case 4:
                    shape = ShapesController.createCube();
                    break;
                case 5:
                    shape = ShapesController.createSphere();
                    break;
                case 6:
                    shape = ShapesController.createCylinder();
                    break;
                case 7:
                case -1:
                    IOManager.showMessage("Espero que haya disfrutado este programa. Nos vemos.");
                    continueMenu = false;
                    break;
                default:
                    IOManager.showMessage("Opci\u00F3n incorrecta, seleccione una opci\u00F3n v\u00E1lida");
            }

            if (shape != null) {
                ShapesController.showShape(shape);
            }
        }
    }

}
