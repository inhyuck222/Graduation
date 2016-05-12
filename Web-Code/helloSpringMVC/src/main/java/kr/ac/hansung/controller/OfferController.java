package kr.ac.hansung.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.dao.Offer;
import kr.ac.hansung.service.OfferService;

@Controller //단지 자동 클래스 빈 등록
public class OfferController {
	
	private OfferService offerService;

	@Autowired //auto-wiring by type to OfferService //단지 생성시 DI 주입
	public void setOfferService(OfferService offerService) {
		this.offerService = offerService;
	}
	
	
	@RequestMapping("/offers")
	public String showOffers(Model model){	
		
		List<Offer> offers = offerService.getCurrent();
		model.addAttribute("offers", offers);
		
		return "offers";
		
	}

	
	@RequestMapping("/createOffer")
	public String createOffer(){	
		

		return "createOffer";
		
	}
	
}
