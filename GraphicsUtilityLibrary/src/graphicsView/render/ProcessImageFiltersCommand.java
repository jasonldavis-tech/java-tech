package graphicsView.render;

import commonModel.RenderEngineModel;
import graphics.ImageFilterUtil;

public class ProcessImageFiltersCommand implements Runnable {
	RenderEngineModel model;
	
	public ProcessImageFiltersCommand(final RenderEngineModel model) {
		this.model = model;
	}
	
	@Override
	public void run() {
		if (model.isToggleAddFilterOn()) {
			if (model.getAddOffset()<100) {
				model.setAddOffset(model.getAddOffset()+2);
			}
			
			model.setBufferedImage(
					ImageFilterUtil.filterImageAdd(model.getBufferedImage(),50+model.getAddOffset()));
		}
		
		if (model.isToggleSubtractFilterOn()) {
			if (model.getSubtractOffset()<100) {
				model.setSubtractOffset(model.getSubtractOffset()+2);
			}
			model.setBufferedImage(ImageFilterUtil.filterImageSubtract(model.getBufferedImage(),50+model.getSubtractOffset()));
		}
	}
}
